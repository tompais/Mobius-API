package com.coder_rangers.mobius_api.notifications.redis.messages

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Positive

data class TestFinishedMessage(
    @field:Positive
    val patientId: Long,

    @field:NotEmpty
    val guardianEmails: Set<@NotBlank String>
) : AbstractMessage()
