package com.optimizer.features

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.markup.HighlighterTargetArea
import com.intellij.openapi.editor.markup.MarkupModel
import com.intellij.openapi.editor.markup.TextAttributes
import java.awt.Color
import com.intellij.openapi.util.TextRange


object ProblemHighlighter {

    fun highlightProblems(editor: Editor, problems: List<Problem>) {
        val markupModel: MarkupModel = editor.markupModel

        for (problem in problems) {
            val highlightRange = problem.range
            val highlightColor = TextAttributes(
                null, Color.RED, null, null, 0
            )
            markupModel.addRangeHighlighter(
                highlightRange.startOffset,
                highlightRange.endOffset,
                0,
                highlightColor,
                HighlighterTargetArea.EXACT_RANGE
            )
        }
    }

    data class Problem(val range: TextRange, val description: String)
}
