package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers

class TextTestGameAnswersRequest(
    category: Game.Category,
    gameId: Long,
    patientTaskAnswersList: List<PatientTaskAnswers<String>>
) : TestGameAnswersRequest<String>(category, gameId, patientTaskAnswersList)
