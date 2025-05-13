import './App.css';
import { useState } from 'react';

function App() {
  const [columns, setColumns] = useState({
    new: { name: 'New', items: [] },
    inProgress: { name: 'In Progress', items: [] },
    completed: { name: 'Done', items: [] },
  });

  const [newTask, setNewTask] = useState('');
  const [selectedPriority, setSelectedPriority] = useState('Medium');
  const [activeColumn, setActiveColumn] = useState('new');

  const addNewTask = () => {
    if (newTask.trim() === '') return;

    const updatedColumns = { ...columns };
    updatedColumns[activeColumn].items.push({
      id: Date.now().toString(),
      content: newTask,
      priority: selectedPriority,
    });

    setColumns(updatedColumns);
    setNewTask('');
  };

  const handleDrop = (e, columnId) => {
    e.preventDefault();
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
              <option value={columnId} key={columnId}>
                {columns[columnId].name}
              </option>
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
            <div key={columnId} className="column"  onDrop={e => handleDrop(e, columnId)}>
              <div className="column-title">{columns[columnId].name}</div>

              <div>
                {columns[columnId].items.length === 0 ? (
                  <p className="text-center">No tasks</p>
                ) : (
                  columns[columnId].items.map(item => (
                    <div
                      key={item.id}
                      className={`task ${item.priority.toLowerCase()}`}
                      draggable
                     
                    >
                      <span>{item.content}</span>
                      <span>{item.priority}</span>
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