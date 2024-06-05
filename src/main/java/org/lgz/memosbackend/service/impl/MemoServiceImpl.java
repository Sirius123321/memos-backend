package org.lgz.memosbackend.service.impl;

import org.lgz.memosbackend.database.dao.MemoRepository;
import org.lgz.memosbackend.database.dao.UserRepository;
import org.lgz.memosbackend.database.model.MemoModel;
import org.lgz.memosbackend.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class MemoServiceImpl implements MemoService {
    private final MemoRepository memoRepository;
    private final UserRepository userRepository;

    @Autowired
    public MemoServiceImpl(MemoRepository memoRepository, UserRepository userRepository) {
        this.memoRepository = memoRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Mono<MemoModel> getMemoById(Long memoId) {
        return memoRepository.findById(memoId)
                .map(memo -> {
                    MemoModel memoModel = new MemoModel();
                    memoModel.setId(memo.getId());
                    memoModel.setContent(memo.getContent());
//                    memoModel.setSharedWith(memo.getSharedWith().stream().map(UserModel::getId).collect(Collectors.toList()));
                    return memoModel;
                });
    }

    @Override
    public Mono<Void> updateMemo(MemoModel memoModel) {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> (UserDetails) ctx.getAuthentication().getPrincipal())
                .flatMap(userDetails -> userRepository.findByUsername(userDetails.getUsername()))
                .flatMap(currentUser -> memoRepository.findById(memoModel.getId())
                        .filter(memo -> memo.getOwner().getId().equals(currentUser.getId()) || memo.getSharedWith().contains(currentUser))
                        .flatMap(memo -> {
                            memo.setContent(memoModel.getContent());
                            return memoRepository.save(memo);
                        })
                ).then();
    }

    @Override
    public Mono<Void> deleteMemoById(Long memoId) {
        return ReactiveSecurityContextHolder.getContext()
                .map(ctx -> (UserDetails) ctx.getAuthentication().getPrincipal())
                .flatMap(userDetails -> userRepository.findByUsername(userDetails.getUsername()))
                .flatMap(currentUser -> memoRepository.findById(memoId)
                        .filter(memo -> memo.getOwner().getId().equals(currentUser.getId()))
                        .flatMap(memoRepository::delete)
                ).then();
    }

    @Override
    public Mono<Void> shareMemoToUser(Long memoId, Long userId) {
        return memoRepository.findById(memoId)
                .flatMap(memo -> userRepository.findById(userId)
                        .flatMap(user -> {
                            memo.getSharedWith().add(user);
                            return memoRepository.save(memo);
                        })
                ).then();
    }

    @Override
    public Mono<Void> deleteShareMemoToUser(Long memoId, Long userId) {
        return memoRepository.findById(memoId)
                .flatMap(memo -> userRepository.findById(userId)
                        .flatMap(user -> {
                            memo.getSharedWith().remove(user);
                            return memoRepository.save(memo);
                        })
                ).then();
    }

    @Override
    public Mono<Boolean> isMemoShareToUser(Long memoId, Long userId) {
        return memoRepository.findById(memoId)
                .flatMap(memo -> userRepository.findById(userId)
                        .map(user -> memo.getSharedWith().contains(user))
                );
    }
}


