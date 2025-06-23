const express = require('express');
const bodyParser = require('body-parser');
const app = express();
const port = 3000;

// Middleware
app.use(bodyParser.json());

// In-memory student list
let students = [];
let idCounter = 1;

// ✅ Create student
app.post('/api/students', (req, res) => {
  const { name, age, course } = req.body;

  // Basic validation
  if (!name || !age || !course) {
    return res.status(400).json({ error: 'Name, age, and course are required.' });
  }

  const student = { id: idCounter++, name, age, course };
  students.push(student);
  res.status(201).json(student);
});

// 📥 Get all students
app.get('/api/students', (req, res) => {
  res.json(students);
});

// 📥 Get single student by ID
app.get('/api/students/:id', (req, res) => {
  const id = parseInt(req.params.id);
  const student = students.find(s => s.id === id);

  if (!student) {
    return res.status(404).json({ error: 'Student not found.' });
  }

  res.json(student);
});

// ✏️ Update student by ID
app.put('/api/students/:id', (req, res) => {
  const id = parseInt(req.params.id);
  const student = students.find(s => s.id === id);

  if (!student) {
    return res.status(404).json({ error: 'Student not found.' });
  }

  const { name, age, course } = req.body;

  // Optional update validation
  if (!name || !age || !course) {
    return res.status(400).json({ error: 'Name, age, and course are required.' });
  }

  student.name = name;
  student.age = age;
  student.course = course;

  res.json(student);
});

// ❌ Delete student by ID
app.delete('/api/students/:id', (req, res) => {
  const id = parseInt(req.params.id);
  const index = students.findIndex(s => s.id === id);

  if (index === -1) {
    return res.status(404).json({ error: 'Student not found.' });
  }

  const deletedStudent = students.splice(index, 1)[0];
  res.json(deletedStudent);
});

// 🚀 Start server
app.listen(port, () => {
  console.log(`✅ Server is running at http://localhost:${port}`);
});
