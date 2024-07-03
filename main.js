let screen = document.getElementById("screen");
let buttons = document.querySelectorAll("button");
let screenValue = "";

buttons.forEach((button) => {
  button.addEventListener("click", (e) => {
    let buttonText;
    if (e.target.tagName.toLowerCase() === "i") {
      buttonText = e.target.parentElement.getAttribute("data-value");
    } else {
      buttonText = e.target.innerText;
    }

    console.log("Button text is ", buttonText);
    if (buttonText == "C") {
      screenValue = "";
      screen.value = screenValue;
    } else if (buttonText == "=") {
      screen.value = eval(screenValue);
    } else if (buttonText == "+/-") {
      screenValue =
        screenValue.charAt(0) === "-"
          ? screenValue.slice(1)
          : "-" + screenValue;
      screen.value = screenValue;
    } else {
      screenValue += buttonText;
      screen.value = screenValue;
    }
  });
});
