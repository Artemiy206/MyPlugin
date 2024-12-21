package com.optimizer.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import com.optimizer.utils.FileContentReader

class AnalyzeCodeForPatternsAction : AnAction("Анализ кода на наличие паттернов") {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project

        // Получение текста текущего файла
        val fileContent = FileContentReader.getCurrentFileContent(project)

        if (fileContent.isBlank()) {
            Messages.showErrorDialog("Не найдено содержимое в текущем файле.", "Ошибка")
            return
        }

        // Анализируем содержимое файла
        val recommendations = analyzePatterns(fileContent)

        // Показать рекомендации
        Messages.showInfoMessage(recommendations, "Рекомендации по оптимизации")
    }

    private fun analyzePatterns(code: String): String {
        val recommendations = mutableListOf<String>()

        // Проверяем наличие пузырьковой сортировки
        if (code.contains("for") && code.contains("if") && code.contains("swap")) {
            recommendations.add("Обнаружен пузырьковый алгоритм сортировки. Рекомендуется использовать сортировку слиянием или быструю сортировку для повышения производительности.")
        }

        // Проверяем использование линейного поиска
        if (code.contains("for") && code.contains("==")) {
            recommendations.add("Обнаружен линейный поиск. Рекомендуется использовать бинарный поиск, если данные отсортированы.")
        }

        // Проверяем наличие вложенных циклов
        val loopCount = code.split("for").size - 1
        if (loopCount > 2) {
            recommendations.add("Обнаружены вложенные циклы. Рекомендуется уменьшить их глубину или использовать более эффективные алгоритмы.")
        }

        return if (recommendations.isEmpty()) {
            "Оптимизационные рекомендации отсутствуют."
        } else {
            recommendations.joinToString("\n")
        }
    }
}
