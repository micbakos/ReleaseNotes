package com.micbakos.releasenotes.providers

import com.micbakos.releasenotes.Arguments
import com.micbakos.releasenotes.Output

fun arguments(
    directory: String = "src/test/resources/normal/both",
    variant: String = "project",
    fromCommit: String = "d0625b6fd42eaa046df16cecaff2ba7eb625c813",
    toCommit: String = "385fe2928332e2b13e24ac73aae8c113e59d9782",
    output: Output = Output.StdOutput
) = Arguments(
    directory = directory,
    variant = variant,
    fromCommit = fromCommit,
    toCommit = toCommit,
    output = output
)
