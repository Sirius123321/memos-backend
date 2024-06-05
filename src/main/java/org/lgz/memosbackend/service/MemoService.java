package org.lgz.memosbackend.service;

import org.lgz.memosbackend.database.model.MemoModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MemoService {

    Mono<MemoModel> getMemoById(Long memoId);

    Mono<Void> updateMemo(MemoModel memoModel);

    Mono<Void> deleteMemoById(Long memoId);

    Mono<Void> shareMemoToUser(Long memoId, Long userId);

    Mono<Void> deleteShareMemoToUser(Long memoId, Long userId);

    Mono<Boolean> isMemoShareToUser(Long memoId, Long userId);
}
