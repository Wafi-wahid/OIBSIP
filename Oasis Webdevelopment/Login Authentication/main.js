let signupBtn = document.getElementById("signupBtn");
let signinBtn = document.getElementById("signinBtn");
let nameField = document.getElementById("nameField");
let title = document.getElementById("title");

signinBtn.addEventListener("click", function () {
  nameField.style.maxHeight = "0";
  title.innerHTML = "Sign In";
  signupBtn.classList.add("disable");
  signinBtn.classList.remove("disable");
  signupBtn.setAttribute("type", "button");
  signinBtn.setAttribute("type", "submit");
});

signupBtn.addEventListener("click", function () {
  nameField.style.maxHeight = "60px";
  title.innerHTML = "Sign Up";
  signupBtn.classList.remove("disable");
  signinBtn.classList.add("disable");
  signupBtn.setAttribute("type", "submit");
  signinBtn.setAttribute("type", "button");
});

let users = JSON.parse(localStorage.getItem("users")) || {}; // store user information

document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("authForm");

  form.addEventListener("submit", function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    if (title.innerHTML === "Sign Up") {
      if (users[username]) {
        alert("Username already exists!");
        return;
      }

      // hash password
      const hashedPassword = bcrypt.hashSync(password, 10);

      // store user information
      users[username] = {
        email: email,
        password: hashedPassword,
      };

      localStorage.setItem("users", JSON.stringify(users));

      alert("User signed up successfully!");
    } else if (title.innerHTML === "Sign In") {
      if (
        users[username] &&
        bcrypt.compareSync(password, users[username].password)
      ) {
        alert("User authenticated successfully!");
        // store session
        sessionStorage.setItem("loggedInUser", username);
        // redirect to secured page
        window.location.href = "secured.html";
      } else {
        alert("Invalid credentials!");
      }
    }
  });
});
