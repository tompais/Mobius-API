package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest

class AttentionGameAnswersRequest(
    category: Game.Category,
    gameId: Long,
    isTestGame: Boolean,
    patientTaskAnswersRequestList: List<PatientTaskAnswersRequest<Char>>
) : GameAnswersRequest<Char>(category, gameId, isTestGame, patientTaskAnswersRequestList)
