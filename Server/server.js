let express = require("express");
let app = express();
let bodyParser = require("body-parser");
let mysql = require("mysql");

app.use(bodyParser.json());
app.use(
  bodyParser.urlencoded({
    extended: true,
  })
);

app.get("/", function (req, res) {
  return res.send({ error: true, message: "Test Employee Web API" });
});

let dbConn = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "ass08_database",
});

dbConn.connect();

app.get("/getAllEmp", function (req, res) {
  dbConn.query("SELECT * FROM employee", function (error, results, fields) {
    if (error) throw error;
    return res.send(results);
  });
});

app.post("/insertEmp", function (req, res) {
  let emp = req.body;

  if (!emp) {
    return res
      .status(400)
      .send({ error: true, message: "Please provide employee" });
  }

  dbConn.query("INSERT INTO employee SET ?", emp, function (
    error,
    results,
    fields
  ) {
    if (error) throw error;
    return res.send(results);
  });
});

app.listen(3000, function () {
  console.log("Node app is running on port 3000");
});

module.exports = app;
