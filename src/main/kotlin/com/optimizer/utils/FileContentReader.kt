package com.optimizer.utils

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project

object FileContentReader {

    /**
     * Возвращает содержимое текущего открытого файла.
     *
     * @param project Текущий проект.
     * @return Содержимое файла или пустая строка, если файл не найден.
     */
    fun getCurrentFileContent(project: Project?): String {
        if (project == null) return ""

        // Получаем текущий редактор
        val editor = FileEditorManager.getInstance(project).selectedTextEditor
            ?: return ""

        // Получаем документ, связанный с редактором
        val document = editor.document

        // Возвращаем текст документа
        return document.text
    }
}
