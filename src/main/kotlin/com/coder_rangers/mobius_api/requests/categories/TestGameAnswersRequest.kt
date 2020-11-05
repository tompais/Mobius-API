package com.coder_rangers.mobius_api.requests.categories

import com.coder_rangers.mobius_api.models.Game
import com.coder_rangers.mobius_api.requests.PatientTaskAnswers
import com.coder_rangers.mobius_api.validators.annotations.ValidTestGameCategory
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonSubTypes.Type
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeInfo.As
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Positive

@JsonTypeInfo(
    use = Id.NAME,
    include = As.EXISTING_PROPERTY,
    property = "category",
    visible = true
)
@JsonSubTypes(
    Type(value = OrientationTestGameAnswersRequest::class, name = "orientation"),
    Type(value = FixationTestGameAnswersRequest::class, name = "fixation"),
    Type(value = CalculationTestGameAnswersRequest::class, name = "calculation"),
    Type(value = AttentionTestGameAnswersRequest::class, name = "attention")
)
open class TestGameAnswersRequest<T>(
    @ValidTestGameCategory
    val category: Game.Category,

    @field:Positive
    val gameId: Long,

    @field:NotEmpty
    val patientTaskAnswersList: List<PatientTaskAnswers<T>>
)
