document.addEventListener("DOMContentLoaded", () => {
  function updateTime() {
    const now = new Date();
    const formattedTime = now.toLocaleTimeString();
    document.getElementById("datetime").innerText = formattedTime;
  }

  setInterval(updateTime, 1000);
  updateTime(); // initial call to display time immediately
});
