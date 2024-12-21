package com.optimizer.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

class EducationAction : AnAction("Educational Materials") {
    override fun actionPerformed(event: AnActionEvent) {
        val material = """
            Optimization Techniques:
            1. Sorting: Use Merge Sort (O(n log n)) instead of Bubble Sort (O(n^2)).
            2. Search: Use Binary Search (O(log n)) instead of Linear Search (O(n)).
            3. Memory Optimization: Use data structures like HashMap or TreeSet for faster access.

            Example:
            Bubble Sort -> Merge Sort:
            int[] arr = {5, 2, 9, 1, 5, 6};
            Arrays.sort(arr);  // Optimized
        """.trimIndent()

        Messages.showInfoMessage(material, "Educational Materials")
    }
}
