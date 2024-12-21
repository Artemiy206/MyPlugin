package com.optimizer.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.optimizer.features.InteractiveTaskManager

class StartInteractiveTaskAction : AnAction("Интерактивные задания") {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.project
        InteractiveTaskManager.startTask(project)
    }
}
