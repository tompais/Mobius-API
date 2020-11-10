package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest

class AttentionTestGameAnswersRequest(
    category: Game.Category,
    gameId: Long,
    patientTaskAnswersRequestList: List<PatientTaskAnswersRequest<Char>>
) : TestGameAnswersRequest<Char>(category, gameId, patientTaskAnswersRequestList)
