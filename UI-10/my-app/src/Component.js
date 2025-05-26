import './App.css';
import { useState } from 'react';

function App() {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState('');
  const [selectedPriority, setSelectedPriority] = useState('Medium');
  const [activeColumn, setActiveColumn] = useState('new');

  const addNewTask = () => {
    if (newTask.trim() === '') return;

    setTasks([
      ...tasks,
      {
        id: Date.now().toString(),
        content: newTask,
        priority: selectedPriority,
        status: activeColumn,
      }
    ]);

    setNewTask('');
  };

  const removeTask = taskId => {
    setTasks(tasks.filter(task => task.id !== taskId));
  };

  const columns = {
    new: 'New',
    inProgress: 'In Progress',
    completed: 'Done',
  };

  return (
    <div className="container">
      <div className="wrapper">
        <h1 className="title">Task Manager</h1>

        <div className="task-form">
          <input
            type="text"
            value={newTask}
            onChange={e => setNewTask(e.target.value)}
            placeholder="Add a new task..."
            onKeyDown={e => e.key === 'Enter' && addNewTask()}
          />

          <select value={activeColumn} onChange={e => setActiveColumn(e.target.value)}>
            {Object.keys(columns).map(columnId => (
              <option value={columnId} key={columnId}>{columns[columnId]}</option>
            ))}
          </select>

          <select value={selectedPriority} onChange={e => setSelectedPriority(e.target.value)}>
            <option value="High">High</option>
            <option value="Medium">Medium</option>
            <option value="Low">Low</option>
          </select>

          <button onClick={addNewTask}>Add</button>
        </div>

        <div className="columns">
          {Object.keys(columns).map(columnId => (
            <div key={columnId} className="column" onDragOver={handleDragOver} onDrop={e => handleDrop(e, columnId)}>
              <div className="column-title">{columns[columnId]}</div>

              <div>
                {tasks.filter(task => task.status === columnId).length === 0 ? (
                  <p className="text-center">No tasks</p>
                ) : (
                  tasks
                    .filter(task => task.status === columnId)
                    .map(task => (
                      <div
                        key={task.id}
                        className={`task ${task.priority.toLowerCase()}`}
                        draggable
                        onDragStart={() => handleDragStart(task)}
                      >
                        <span>{task.content}</span>
                        <span>{task.priority}</span>
                        <button onClick={() => removeTask(task.id)}>✖</button>
                      </div>
                    ))
                )}
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
}

export default App;
