package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers

class OrientationTestGameAnswersRequest(
    category: Game.Category,
    gameId: Long,
    patientTaskAnswers: List<PatientTaskAnswers<Boolean>>
) : TestGameAnswersRequest<Boolean>(category, gameId, patientTaskAnswers)
