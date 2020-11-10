package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.PatientTaskAnswersRequest

class TextTestGameAnswersRequest(
    category: Game.Category,
    gameId: Long,
    patientTaskAnswersRequestList: List<PatientTaskAnswersRequest<String>>
) : TestGameAnswersRequest<String>(category, gameId, patientTaskAnswersRequestList)
