package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game.Category
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest

class AttentionGameAnswersRequest(
    category: Category,
    gameId: Long,
    areTestGameAnswers: Boolean,
    patientTaskAnswersRequestList: List<PatientTaskAnswersRequest<Char>>
) : GameAnswersRequest<Char>(category, gameId, areTestGameAnswers, patientTaskAnswersRequestList)
