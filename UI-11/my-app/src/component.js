import './App.css';
import { useState } from 'react';

function App() {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState('');
  const [selectedPriority, setSelectedPriority] = useState('Medium');
  const [activeColumn, setActiveColumn] = useState('col_1');
  const [activeStatus, setActiveStatus] = useState('New');

  const columns = {
    col_1: 'New',
    col_2: 'In Progress',
    col_3: 'Done',
  };

  const status = {
    New: 'New',
    In_progress: 'In Progress',
    Done: 'Done',
  }

  const addNewTask = () => {
    if (newTask.trim() === '') return;

    setTasks([
      tasks,
      {
        id: Date.now().toString(),
        content: newTask,
        priority: selectedPriority,
        columnId: activeColumn,
        status: activeStatus,
      },
    ]);

    setNewTask('');
  };

  const removeTask = taskId => {
    setTasks(tasks.filter(task => task.id !== taskId));
  };

  const handleDragStart = item => {
    if (item.columnId === 'col_3') return;
    setDraggedItem(item);
  };

  const handleDragOver = e => {
    e.preventDefault();
  };

  const handleDrop = (e, newColumnId) => {
    e.preventDefault();
    if (!draggedItem || draggedItem.columnId === newColumnId) return;

    setTasks(tasks.map(task =>
      task.id === draggedItem.id ? { ...task, columnId: newColumnId, status: columns[newColumnId] } : task
    ));
    setDraggedItem(null);
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
            <div
              key={columnId}
              className="column"
              onDragOver={handleDragOver}
              onDrop={e => handleDrop(e, columnId)}
            >
              <div className="column-title">{columns[columnId]}</div>

              <div>
                {tasks.filter(task => task.columnId === columnId).length === 0 ? (
                  <p className="text-center">No tasks</p>
                ) : (
                  tasks
                    .filter(task => task.columnId === columnId)
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
