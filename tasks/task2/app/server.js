'use strict';

const express = require('express');
const bodyParser = require('body-parser')
const fs = require('fs');
const crypto = require('crypto');

const Pool = require('pg').Pool
const pool = new Pool({
    user: 'docker',
    host: 'localhost',
    database: 'myapp',
    password: 'docker1245',
    port: 5432,
});

// Constants
const PORT = 8080;
const HOST = '0.0.0.0';

let salt;
fs.readFile('./salt.txt', 'utf8', function(err, contents) {
  salt = contents;
});

function getPasswordHash(password) {
  const sha = crypto.createHash('sha1');
  return sha.update(salt + password).digest('hex');
}

// App
const app = express();
app.use(express.json());


app.get('/', (req, res) => {
  res.send('Sample secure application\n');
});

app.get('/users', (req, res) => {
  const username = req.query.username;
  pool.query("SELECT * FROM users WHERE user_name = '" + username + "'", (error, results) => {
    if (error) {
      throw error
    }
    if(results.rows.length > 0) {
      res.status(200).json({result: "User exists"});
    } else {
      res.status(200).json({result: "User not exist"});
    }
  });
});

app.post('/users', (req, res) => {
  const userData = req.body;
  pool.query('INSERT INTO users (user_id, user_name, user_fname, user_lname, password) VALUES ((SELECT MAX(user_id)+1 FROM users), $1, $2, $3, $4);', 
      [userData.userName, userData.userFName, userData.userLName, getPasswordHash(userData.password)], (error, results) => {

    if(error) {
      throw error;
    }

    res.status(201).json({result: "OK"});

  });
});

app.post('/login', (req, res) => {
  const userSec = req.body;
  pool.query('SELECT * FROM users WHERE user_name = $1 AND password = $2;',[userSec.userName, getPasswordHash(userSec.password)], (error, results) => {
    if (error) {
      res.status(500).json({result: error});
    }
    if(results.rows.length === 1) {
      res.status(200).json({result: "Logged in OK"});
    } else {
      res.status(401).json({result: "Username or password is incorrect"});
    }
  });
});



app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);
