const express = require('express');
const path = require('path');

const app = express();

app.use(express.static(path.join(__dirname, 'public')));

app.get('/', (req, res) =>{
    res.send(path.join(__dirname, 'public', 'index.html'));
})

app.listen(3000, () =>{
    console.log('server is running on http://localhost:3000')
})