package com.optimizer.features

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

object InteractiveTaskManager {

    private val tasks = listOf(
        Task(
            description = """
                Задача:
                Код содержит неэффективный алгоритм сортировки (Bubble Sort, O(n^2)).
                Ваша задача: заменить его на более эффективный алгоритм, например, Merge Sort (O(n log n)).
            """.trimIndent(),
            originalCode = """
                void bubbleSort(int arr[], int n) {
                    for (int i = 0; i < n - 1; i++) {
                        for (int j = 0; j < n - i - 1; j++) {
                            if (arr[j] > arr[j + 1]) {
                                int temp = arr[j];
                                arr[j] = arr[j + 1];
                                arr[j + 1] = temp;
                            }
                        }
                    }
                }
            """.trimIndent(),
            hint = "Используйте Merge Sort для лучшей производительности.",
            optimizedCode = """
                void mergeSort(int arr[], int left, int right) {
                    if (left < right) {
                        int mid = left + (right - left) / 2;

                        mergeSort(arr, left, mid);
                        mergeSort(arr, mid + 1, right);

                        merge(arr, left, mid, right);
                    }
                }
                
                void merge(int arr[], int left, int mid, int right) {
                    int n1 = mid - left + 1;
                    int n2 = right - mid;

                    int L[n1], R[n2];

                    for (int i = 0; i < n1; i++) {
                        L[i] = arr[left + i];
                    }
                    for (int j = 0; j < n2; j++) {
                        R[j] = arr[mid + 1 + j];
                    }

                    int i = 0, j = 0, k = left;
                    while (i < n1 && j < n2) {
                        if (L[i] <= R[j]) {
                            arr[k] = L[i];
                            i++;
                        } else {
                            arr[k] = R[j];
                            j++;
                        }
                        k++;
                    }

                    while (i < n1) {
                        arr[k] = L[i];
                        i++;
                        k++;
                    }

                    while (j < n2) {
                        arr[k] = R[j];
                        j++;
                        k++;
                    }
                }
            """.trimIndent()
        )
    )

    fun startTask(project: Project?) {
        if (project == null) return

        val task = tasks.firstOrNull()
        if (task == null) {
            Messages.showInfoMessage("Нет доступных задач.", "Интерактивные Задания")
            return
        }

        Messages.showInfoMessage(task.description, "Интерактивная Задача")

        val result = Messages.showMultilineInputDialog(
            project,
            "Введите оптимизированный код:",
            "Решение Задачи",
            task.originalCode,
            null,
            null
        )

        if (result == task.optimizedCode) {
            Messages.showInfoMessage("Решение верное! Вы улучшили код.", "Результат")
        } else {
            Messages.showInfoMessage("Решение неверное. Попробуйте ещё раз. Подсказка: ${task.hint}", "Результат")
        }
    }

    data class Task(
        val description: String,
        val originalCode: String,
        val hint: String,
        val optimizedCode: String
    )
}
