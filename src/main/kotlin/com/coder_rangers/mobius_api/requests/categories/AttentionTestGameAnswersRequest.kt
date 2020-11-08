package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers

class AttentionTestGameAnswersRequest(
    category: Game.Category,
    gameId: Long,
    patientTaskAnswersList: List<PatientTaskAnswers<Char>>
) : TestGameAnswersRequest<Char>(category, gameId, patientTaskAnswersList)
