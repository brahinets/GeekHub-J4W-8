package org.geekhub.lesson6.task2;

import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.util.Collections.singletonList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class TaskManagerTest {

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void tasksDailyLimit_shouldBeNonNegative() {
        new TaskManagerImpl(-1);
    }

    @Test
    public void shouldAccumulateTasks_whenNoDailyLimit() {
        TaskManager taskManager = new TaskManagerImpl();

        int tasksCount = taskManager.addTask(new Task("Home", "Clean kitchen", today()));

        assertEquals(tasksCount, 1);
    }

    @Test
    public void shouldReturnTotalNumberOfTasks_whenNewTaskInserted() {
        TaskManager taskManager = new TaskManagerImpl();
        int tasksToInsert = ThreadLocalRandom.current().nextInt(1, 100);

        for(int i = 0; i < tasksToInsert; i++) {
            taskManager.addTask(new Task("Home", "Clean kitchen-"+i, today()));
        }
        int totalTasks = taskManager.addTask(new Task("Home", "Clean kitchen-" + tasksToInsert, today()));

        assertEquals(totalTasks, tasksToInsert + 1);
    }

    @Test(expectedExceptions = TaskLimitExceededException.class)
    public void shouldFail_whenDailyLimitExceeded() {
        int tasksDailyLimit = 1;
        TaskManager taskManager = new TaskManagerImpl(tasksDailyLimit);

        taskManager.addTask(new Task("Work", "Drink coffee", today()));
        taskManager.addTask(new Task("Health", "Run marathon", tomorrow()));
        taskManager.addTask(new Task("Home", "Cook dishes", tomorrow()));
    }

    @Test
    public void shouldAccumulateTasks_whenDailyLimitNotExceeded() {
        int tasksDailyLimit = 1;
        TaskManager taskManager = new TaskManagerImpl(tasksDailyLimit);

        taskManager.addTask(new Task("Work", "Drink coffee", today()));
        taskManager.addTask(new Task("Health", "Run marathon", tomorrow()));
        taskManager.addTask(new Task("Home", "Cook dishes", yesterday()));
    }

    @Test
    public void shouldRemoveAllTasksByDate_whenTasksExistsForDate() {
        TaskManager taskManager = new TaskManagerImpl();
        taskManager.addTask(new Task("Home", "Clean kitchen", yesterday()));
        taskManager.addTask(new Task("Health", "Run marathon", yesterday()));
        taskManager.addTask(new Task("Work", "Drink coffee", today()));

        boolean removed = taskManager.removeTasks(yesterday());

        assertTrue(removed);
        assertEquals(taskManager.getAllTasks().size(), 1);
    }

    @Test
    public void shouldNotRemoveTasksByDate_whenTasksDoesNotExistsForDate() {
        TaskManager taskManager = new TaskManagerImpl();
        taskManager.addTask(new Task("Work", "Drink coffee", today()));

        boolean removed = taskManager.removeTasks(yesterday());

        assertFalse(removed);
    }

    @Test
    public void shouldReturnCopyOfCategories() {
        TaskManager taskManager = new TaskManagerImpl();
        taskManager.addTask(new Task("Home", "Clean kitchen", today()));

        Set<String> categories = taskManager.getCategories();
        categories.add("Illegal");

        assertEquals(taskManager.getCategories(), Set.of("Home"));
    }

    @Test
    public void shouldReturnNoCategories_whenNoTasks() {
        TaskManager taskManager = new TaskManagerImpl();

        Set<String> categories = taskManager.getCategories();

        assertTrue(categories.isEmpty());
    }

    @Test
    public void shouldReturnUniqueCategories() {
        TaskManager taskManager = new TaskManagerImpl();
        taskManager.addTask(new Task("Home", "Clean kitchen", today()));
        taskManager.addTask(new Task("Work", "Drink coffee", today()));
        taskManager.addTask(new Task("Work", "Write code", today()));
        taskManager.addTask(new Task("Health", "Run marathon", today()));

        Set<String> categories = taskManager.getCategories();

        assertEquals(categories, Set.of("Home", "Work", "Health"));
    }

    @Test
    public void shouldReturnCopyOfTasksGroupedByCategories() {
        TaskManager taskManager = new TaskManagerImpl();
        taskManager.addTask(new Task("Home", "Clean kitchen", today()));

        Map<String, List<Task>> tasksByCategories = taskManager.getTasksByCategories();
        tasksByCategories.put("Illegal", singletonList(new Task("Illegal", "Smoke", today())));
        tasksByCategories.get("Home").clear();

        assertEquals(taskManager.getTasksByCategories().size(), 1);
        assertCollectionEqualsIgnoringOrder(
            taskManager.getTasksByCategories().get("Home"),
            List.of(new Task("Home", "Clean kitchen", today()))
        );
    }

    @Test
    public void shouldReturnEmptyMapOfTasksGroupedByCategories_whenTasksDoesNotExist() {
        TaskManager taskManager = new TaskManagerImpl();

        Map<String, List<Task>> tasksByCategories = taskManager.getTasksByCategories();

        assertTrue(tasksByCategories.isEmpty());
    }

    @Test
    public void shouldReturnTasksGroupedByCategories_whenTasksExists() {
        TaskManager taskManager = new TaskManagerImpl();
        taskManager.addTask(new Task("Home", "Clean kitchen", today()));
        taskManager.addTask(new Task("Work", "Drink coffee", today()));
        taskManager.addTask(new Task("Work", "Write code", today()));
        taskManager.addTask(new Task("Health", "Run marathon", today()));

        Map<String, List<Task>> tasksByCategories = taskManager.getTasksByCategories();

        assertCollectionEqualsIgnoringOrder(
            tasksByCategories.get("Home"),
            Collections.singletonList(new Task("Home", "Clean kitchen", today()))
        );
        assertCollectionEqualsIgnoringOrder(
            tasksByCategories.get("Work"),
            Arrays.asList(
                new Task("Work", "Drink coffee", today()),
                new Task("Work", "Write code", today())
            )
        );
        assertCollectionEqualsIgnoringOrder(
            tasksByCategories.get("Health"),
            Collections.singletonList(new Task("Health", "Run marathon", today()))
        );
    }

    @Test
    public void shouldReturnTasksGroupedByCategoriesSortedByDateDescending_whenTasksExists() {
        TaskManager taskManager = new TaskManagerImpl();
        taskManager.addTask(new Task("Work", "Drink coffee", yesterday()));
        taskManager.addTask(new Task("Work", "Eat cookie", today()));
        taskManager.addTask(new Task("Work", "Write code", tomorrow()));
        taskManager.addTask(new Task("Work", "Write code", yesterday()));
        taskManager.addTask(new Task("Work", "Write code", today()));
        taskManager.addTask(new Task("Work", "Drink whiskey", tomorrow()));

        Map<String, List<Task>> tasksByCategories = taskManager.getTasksByCategories();
        List<Task> workTasks = tasksByCategories.get("Work");

        assertTasksSortedByDateDescending(workTasks);
    }

    @Test
    public void shouldReturnEmptyListOfTasksByCategory_whenTasksDoesNotExistForCategory() {
        TaskManager taskManager = new TaskManagerImpl();

        List<Task> tasksByCategory = taskManager.getTasksByCategory("Health");

        assertTrue(tasksByCategory.isEmpty());
    }

    @Test
    public void shouldReturnTasksByCategory_whenTasksExistsForCategory() {
        TaskManager taskManager = new TaskManagerImpl();
        taskManager.addTask(new Task("Home", "Clean kitchen", today()));
        taskManager.addTask(new Task("Home", "Cook dishes", today()));

        List<Task> tasksByCategory = taskManager.getTasksByCategory("Home");

        List<Task> expectedTasksByCategory = Arrays.asList(
            new Task("Home", "Clean kitchen", today()),
            new Task("Home", "Cook dishes", today())
        );
        assertCollectionEqualsIgnoringOrder(tasksByCategory, expectedTasksByCategory);
    }

    @Test
    public void shouldReturnCopyOfTasksByCategory_whenTasksExistsForCategory() {
        TaskManager taskManager = new TaskManagerImpl();

        List<Task> tasksByCategory = taskManager.getTasksByCategory("Home");
        tasksByCategory.add(new Task("Home", "Cook dishes", today()));

        assertTrue(taskManager.getTasksByCategory("Home").isEmpty());
    }

    @Test
    public void shouldReturnTasksByCategorySortedByDateDescending_whenTasksExistsForCategory() {
        TaskManager taskManager = new TaskManagerImpl();
        taskManager.addTask(new Task("Work", "Drink coffee", yesterday()));
        taskManager.addTask(new Task("Work", "Eat cookie", today()));
        taskManager.addTask(new Task("Work", "Write code", tomorrow()));
        taskManager.addTask(new Task("Work", "Write code", yesterday()));
        taskManager.addTask(new Task("Work", "Write code", today()));
        taskManager.addTask(new Task("Work", "Drink whiskey", tomorrow()));

        List<Task> tasksByCategory = taskManager.getTasksByCategory("Work");

        assertTasksSortedByDateDescending(tasksByCategory);
    }

    private void assertTasksSortedByDateDescending(List<Task> workTasks) {
        assertFalse(workTasks.isEmpty());

        Task latestTask = workTasks.get(0);
        for (int taskIndex = 1; taskIndex < workTasks.size(); taskIndex++) {
            Task earlierTask = workTasks.get(taskIndex);
            assertFalse(earlierTask.getDate().isAfter(latestTask.getDate()));
            latestTask = earlierTask;
        }
    }

    @Test
    public void shouldReturnEmptyListOfTasksForToday_whenNoTasksForToday() {
        TaskManager taskManager = new TaskManagerImpl();

        List<Task> tasksForToday = taskManager.getTasksForToday();

        assertTrue(tasksForToday.isEmpty());
    }

    @Test
    public void shouldReturnCopyOfTasksForTodaySortedByCategoryAscending_whenTasksExistsForToday() {
        TaskManager taskManager = new TaskManagerImpl();

        List<Task> tasksForToday = taskManager.getTasksForToday();
        tasksForToday.add(new Task("Illegal", "Smoke", today()));

        assertTrue(taskManager.getTasksForToday().isEmpty());
    }

    @Test
    public void shouldReturnTasksForTodaySortedByCategoryAscending_whenTasksExistsForToday() {
        TaskManager taskManager = new TaskManagerImpl();
        taskManager.addTask(new Task("Health", "Run marathon", yesterday()));
        taskManager.addTask(new Task("Health", "Run marathon", today()));
        taskManager.addTask(new Task("Work", "Write code", today()));
        taskManager.addTask(new Task("Home", "Clean kitchen", today()));
        taskManager.addTask(new Task("Work", "Drink coffee", tomorrow()));

        List<Task> tasksForToday = taskManager.getTasksForToday();

        List<Task> expectedTasksForToday = Arrays.asList(
            new Task("Health", "Run marathon", today()),
            new Task("Home", "Clean kitchen", today()),
            new Task("Work", "Write code", today())
        );
        assertCollectionEqualsIgnoringOrder(tasksForToday, expectedTasksForToday);
    }

    @Test
    public void shouldReturnCopyOfAllTasks() {
        TaskManager taskManager = new TaskManagerImpl();

        List<Task> tasks = taskManager.getAllTasks();
        tasks.add(new Task("Illegal", "Smoke", today()));

        assertTrue(taskManager.getAllTasks().isEmpty());
    }

    @Test
    public void shouldReturnAllTasks() {
        TaskManager taskManager = new TaskManagerImpl();
        taskManager.addTask(new Task("Health", "Run marathon", yesterday()));
        taskManager.addTask(new Task("Home", "Clean kitchen", today()));
        taskManager.addTask(new Task("Work", "Write code", today()));
        taskManager.addTask(new Task("Work", "Drink coffee", tomorrow()));

        List<Task> actualAllTasks = taskManager.getAllTasks();

        List<Task> expectedAllTasks = Arrays.asList(
            new Task("Health", "Run marathon", yesterday()),
            new Task("Home", "Clean kitchen", today()),
            new Task("Work", "Write code", today()),
            new Task("Work", "Drink coffee", tomorrow())
        );
        assertCollectionEqualsIgnoringOrder(actualAllTasks, expectedAllTasks);
    }

    private <T> void assertCollectionEqualsIgnoringOrder(Collection<T> actual, Collection<T> expected) {
        if (actual == expected) {
            return;
        }

        assertNotNull(actual);
        assertNotNull(expected);

        assertEquals(actual.size(), expected.size(), "Collections are different in size.");
        for (T item : expected) {
            assertTrue(actual.contains(item), "Result collection does not contain expected value.");
        }
    }

    private LocalDate today() {
        return LocalDate.now();
    }

    private LocalDate tomorrow() {
        return LocalDate.now().plusDays(1);
    }

    private LocalDate yesterday() {
        return LocalDate.now().minusDays(1);
    }
}