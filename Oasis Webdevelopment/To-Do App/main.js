document.addEventListener("DOMContentLoaded", () => {
  const storedTasks = JSON.parse(localStorage.getItem("tasks"));
  if (storedTasks) {
    storedTasks.forEach((task) => tasks.push(task));
    updateTasksList();
    updateStats();
  }
});

let tasks = [];
let filter = "all";

const saveTasks = () => {
  localStorage.setItem("tasks", JSON.stringify(tasks));
};

const addTask = () => {
  const taskInput = document.getElementById("taskInput");
  const task = taskInput.value.trim();

  if (task) {
    tasks.push({ text: task, completed: false });
    taskInput.value = "";
    updateTasksList();
    updateStats();
    saveTasks();
  }
};

const toggleTaskComplete = (index) => {
  tasks[index].completed = !tasks[index].completed;
  updateTasksList();
  updateStats();
  saveTasks();
};

const editTask = (index) => {
  const taskInput = document.getElementById("taskInput");
  taskInput.value = tasks[index].text;

  tasks.splice(index, 1);
  updateTasksList();
  updateStats();
  saveTasks();
};

const updateStats = () => {
  const completedTasks = tasks.filter((task) => task.completed).length;
  const totalTasks = tasks.length;
  const progress = totalTasks === 0 ? 0 : (completedTasks / totalTasks) * 100;
  const progressBar = document.getElementById("progress");

  progressBar.style.width = `${progress}%`;

  document.getElementById(
    "numbers"
  ).innerText = `${completedTasks} / ${totalTasks}`;

  if (tasks.length && completedTasks === totalTasks) {
    blastConfetti();
  }
};

const deleteTask = (index) => {
  tasks.splice(index, 1);
  updateTasksList();
  updateStats();
  saveTasks();
};

const deleteAllTasks = () => {
  tasks = [];
  updateTasksList();
  updateStats();
  saveTasks();
};

const updateTasksList = () => {
  const tasksList = document.getElementById("task-list");
  tasksList.innerHTML = "";
  tasks
    .filter(
      (task) =>
        filter === "all" ||
        (filter === "completed" && task.completed) ||
        (filter === "pending" && !task.completed)
    )
    .forEach((task, index) => {
      const listItem = document.createElement("li");
      listItem.innerHTML = `
        <div class="taskItem">
          <div class="task ${task.completed ? "completed" : ""}">
            <input type="checkbox" class="checkbox" ${
              task.completed ? "checked" : ""
            } />
            <p>${task.text}</p>
          </div>
          <div class="icons">
            <i class="fa-solid fa-pen-to-square" style="color: #828dff;" onClick="editTask(${index})"></i>
            <i class="fa-solid fa-trash-can" style="color: #ff2600;" onClick="deleteTask(${index})"></i>
          </div>
        </div>
      `;
      listItem
        .querySelector(".checkbox")
        .addEventListener("change", () => toggleTaskComplete(index));
      tasksList.appendChild(listItem);
    });
};

document.getElementById("newTask").addEventListener("click", function (e) {
  e.preventDefault();
  addTask();
});

const filters = document.querySelectorAll(".filter");
filters.forEach(function (el) {
  el.addEventListener("click", (e) => {
    filters.forEach((tag) => tag.classList.remove("active"));
    el.classList.add("active");
    filter = e.target.dataset.filter;
    updateTasksList();
  });
});

document.querySelector(".delete-all").addEventListener("click", deleteAllTasks);

const blastConfetti = () => {
  const duration = 15 * 1000,
    animationEnd = Date.now() + duration,
    defaults = { startVelocity: 30, spread: 360, ticks: 60, zIndex: 0 };

  function randomInRange(min, max) {
    return Math.random() * (max - min) + min;
  }

  const interval = setInterval(function () {
    const timeLeft = animationEnd - Date.now();

    if (timeLeft <= 0) {
      return clearInterval(interval);
    }

    const particleCount = 50 * (timeLeft / duration);

    // since particles fall down, start a bit higher than random
    confetti(
      Object.assign({}, defaults, {
        particleCount,
        origin: { x: randomInRange(0.1, 0.3), y: Math.random() - 0.2 },
      })
    );
    confetti(
      Object.assign({}, defaults, {
        particleCount,
        origin: { x: randomInRange(0.7, 0.9), y: Math.random() - 0.2 },
      })
    );
  }, 250);
};
