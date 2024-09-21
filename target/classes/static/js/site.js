const postRequestOptions = {
  method: "POST",
  redirect: "follow",
};

async function submitWager() {
  const playerId = document.getElementById("playerId").innerText;
  const amount = +document.getElementById("amount").value;
  const odds = +document.getElementById("odds").value;
  const balance = +document.getElementById("balance").innerText;
  console.log("playerId:", playerId);
  console.log("amount:", amount);
  console.log("odds:", odds);
  console.log("balance:", balance);
  //deduct wager from balance...
  deductWager(playerId, amount);
  //add wager entry to transactions table...
  addWagerEntry(playerId, amount);
  //determine winnings...
  const winnings = calculateWinnings(amount, odds);
  //add winnings to balance...
  console.log("add " + winnings + " winnings to balance...");
  addWinnings(playerId, winnings);
  //add win entry to transactions table...
  addWinningsEntry(playerId, winnings);
}

function addWagerEntry(playerId, amount) {
  fetch(
    "http://localhost:8000/casino/player/" +
      playerId +
      "/transaction/add?transactionType=WAGER&amount=" +
      amount +
      "&playerId=" +
      playerId +
      "&dateTime=" +
      currentDateTime(),
    postRequestOptions
  )
    .then((response) => response.text())
    .then((result) => {
      /*console.log(result)*/
    })
    .catch((error) => console.log("add wager error", error));
}

function addWinningsEntry(playerId, amount) {
  fetch(
    "http://localhost:8000/casino/player/" +
      playerId +
      "/transaction/add?transactionType=WIN&amount=" +
      amount +
      "&playerId=" +
      playerId +
      "&dateTime=" +
      currentDateTime(),
    postRequestOptions
  )
    .then((response) => response.text())
    .then((result) => {
      /*console.log(result)*/
    })
    .catch((error) => console.log("add winnings error", error));
}

function deductWager(playerId, amount) {
  fetch(
    "http://localhost:8000/casino/player/" +
      playerId +
      "/balance/update?amount=-" +
      amount,
    postRequestOptions
  )
    .then((response) => response.text())
    .then((result) => {
      console.log("old balance: " + result);
    })
    .catch((error) => console.log("deduct wager error", error));
}

function addWinnings(playerId, amount) {
  fetch(
    "http://localhost:8000/casino/player/" +
      playerId +
      "/balance/update?amount=" +
      amount,
    postRequestOptions
  )
    .then((response) => response.text())
    .then((result) => console.log("new balance: " + result))
    .catch((error) => console.log("add wager error", error));
}

function calculateWinnings(amount, odds) {
  const randomInt = randomIntFromInterval(1, 100);
  if (randomInt > odds) {
    console.log("WIN");
    return amount + parseFloat(Math.round(amount * (odds / 100)));
  } else {
    console.log("LOSS");
    return 0;
  }
}

function randomIntFromInterval(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min);
}

function setRandomOdds() {
  const rndInt = randomIntFromInterval(1, 100);
  const odds = document.getElementById("odds");
  if (odds) odds.value = rndInt;
}

function currentDateTime() {
  let date = new Date();
  let dateStr =
    date.getFullYear() +
    "-" +
    ("00" + (date.getMonth() + 1)).slice(-2) +
    "-" +
    ("00" + date.getDate()).slice(-2) +
    " " +
    ("00" + date.getHours()).slice(-2) +
    ":" +
    ("00" + date.getMinutes()).slice(-2) +
    ":" +
    ("00" + date.getSeconds()).slice(-2);
  return dateStr;
}

$(document).ready(function () {
  setRandomOdds();
});
