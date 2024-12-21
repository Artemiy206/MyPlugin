package com.optimizer.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import com.optimizer.features.InteractiveTaskManager
import com.optimizer.features.ProblemHighlighter
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.util.TextRange

class AnalyzeCodeAction : AnAction("Analyze Code and Learn") {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project

        if (project == null) {
            Messages.showErrorDialog("Не удалось определить проект.", "Ошибка")
            return
        }

        // Запускаем интерактивную задачу
        InteractiveTaskManager.startTask(project)

        // Пример подсветки
        val editor = EditorFactory.getInstance().allEditors.firstOrNull() ?: return
        ProblemHighlighter.highlightProblems(
            editor,
            listOf(
                ProblemHighlighter.Problem(
                    range = TextRange(0, 10),
                    description = "Неэффективный алгоритм сортировки."
                )
            )
        )
    }
}
