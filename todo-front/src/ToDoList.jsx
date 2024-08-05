import React, { useState, useEffect } from "react";
import axios from "axios";

function ToDoList() {
    const [tasks, setTasks] = useState([]);
    const [newTask, setNewTask] = useState("");
    const [editingIndex, setEditingIndex] = useState(null);
    const [editingTask, setEditingTask] = useState("");
    const [filter, setFilter] = useState("All");

    useEffect(() => {
        getTasks();
    }, []);

    async function getTasks() {
        try {
            const response = await axios.get("http://localhost:8080/api/v1/get-notesalldata");
            const fetchedTasks = response.data.map(task => ({
                id: task.id_note,
                id_note: task.id_note,
                text: task.content,
                isCompleted: task.is_completed
            }));
            setTasks(fetchedTasks);
        } catch (error) {
            console.error("Error fetching tasks:", error);
        }
    }

    async function getDoneNotes() {
        try {
            const response = await axios.get("http://localhost:8080/api/v1/get-donenotes");
            const fetchedTasks = response.data.map(task => ({
                id: task.id_note,
                id_note: task.id_note,
                text: task.content,
                isCompleted: task.is_completed
            }));
            setTasks(fetchedTasks);
        } catch (error) {
            console.error("Error fetching done tasks:", error);
        }
    }

    async function getTodoNotes() {
        try {
            const response = await axios.get("http://localhost:8080/api/v1/get-todonotes");
            const fetchedTasks = response.data.map(task => ({
                id: task.id_note,
                id_note: task.id_note,
                text: task.content,
                isCompleted: task.is_completed
            }));
            setTasks(fetchedTasks);
        } catch (error) {
            console.error("Error fetching todo tasks:", error);
        }
    }

    function handleInputChange(event) {
        setNewTask(event.target.value);
    }

    function handleEditInputChange(event) {
        setEditingTask(event.target.value);
    }

    async function addTask() {
        if (!newTask.trim()) return;
        try {
            const response = await axios.post("http://localhost:8080/api/v1/add-note", { content: newTask, is_completed: 1 });
            const addedTask = {
                id: response.data.id_note,
                id_note: response.data.id_note,
                text: response.data.content,
                isCompleted: response.data.is_completed
            };
            setTasks(prevTasks => [...prevTasks, addedTask]);
            setNewTask("");
            getTasks();
        } catch (error) {
            console.error("Error adding task:", error);
        }
    }

    async function deleteTask(index) {
        try {
            const taskToDelete = tasks[index];
            await axios.delete('http://localhost:8080/api/v1/delete-note', {
                data: { id_note: taskToDelete.id_note }
            });
            setTasks(prevTasks => prevTasks.filter((_, i) => i !== index));
        } catch (error) {
            console.error("Error deleting task:", error);
        }
    }

    async function moveTaskUp(index) {
        if (index === 0) return;
        try {
            const newTasks = [...tasks];
            [newTasks[index - 1], newTasks[index]] = [newTasks[index], newTasks[index - 1]];
            setTasks(newTasks);
        } catch (error) {
            console.error("Error moving task up:", error);
        }
    }

    async function moveTaskDown(index) {
        if (index === tasks.length - 1) return;
        try {
            const newTasks = [...tasks];
            [newTasks[index + 1], newTasks[index]] = [newTasks[index], newTasks[index + 1]];
            setTasks(newTasks);
        } catch (error) {
            console.error("Error moving task down:", error);
        }
    }

    async function updateTask(index) {
        try {
            const updatedTask = {
                ...tasks[index],
                is_completed: tasks[index].isCompleted === 1 ? 2 : 1
            };
            const response = await axios.put('http://localhost:8080/api/v1/update-note', {
                id_note: updatedTask.id_note,
                content: updatedTask.text,
                is_completed: updatedTask.is_completed,
                id_user: updatedTask.id_user || 1
            });
            if (response.status === 200) {
                setTasks(prevTasks => {
                    const newTasks = [...prevTasks];
                    newTasks[index] = {
                        ...updatedTask,
                        text: response.data.content,
                        isCompleted: response.data.is_completed
                    };
                    return newTasks;
                });
                if (filter === "Done") {
                    getDoneNotes();
                } else if (filter === "Todo") {
                    getTodoNotes();
                } else {
                    getTasks();
                }
            }
        } catch (error) {
            console.error("Error updating task:", error);
        }
    }

    async function deleteAllDoneTasks() {
        try {
            await axios.delete("http://localhost:8080/api/v1/delete-all-done-notes");
            setTasks(tasks.filter(task => task.isCompleted != 2));
            getTasks();
        } catch (error) {
            console.error("Error deleting all done tasks:", error);
        }
    }

    async function deleteAllTasks() {
        try {
            await axios.delete("http://localhost:8080/api/v1/delete-all-notes");
            setTasks([]);
        } catch (error) {
            console.error("Error deleting all tasks:", error);
        }
    }

    function startEditing(index) {
        setEditingIndex(index);
        setEditingTask(tasks[index].text);
    }

    async function saveTask() {
        if (editingIndex === null) return;
        try {
            const updatedTask = { ...tasks[editingIndex], text: editingTask };
            const requestBody = {
                id_note: updatedTask.id_note,
                content: editingTask,
                is_completed: updatedTask.isCompleted,
                id_user: updatedTask.id_user || 1
            };
            console.log("Sending data:", requestBody);

            const response = await axios.put(`http://localhost:8080/api/v1/update-note`, requestBody);

            console.log("Received response:", response.data);

            if (response.status === 200) {
                setTasks(prevTasks => {
                    const newTasks = [...prevTasks];
                    newTasks[editingIndex] = {
                        ...updatedTask,
                        id_note: response.data.id_note,
                        text: response.data.content,
                        isCompleted: response.data.is_completed
                    };
                    return newTasks;
                });
                setEditingIndex(null);
                setEditingTask("");
                if (filter === "Done") {
                    getDoneNotes();
                } else if (filter === "Todo") {
                    getTodoNotes();
                } else {
                    getTasks();
                }
            }
        } catch (error) {
            console.error("Error saving task:", error);
        }
    }

    return (
        <div className="to-do-list">
            <h1>To-Do List</h1>
            <div>
                <input
                    type="text"
                    placeholder="Enter a task..."
                    value={newTask}
                    onChange={handleInputChange}
                />
                <button className="add-button" onClick={addTask}>
                    Add
                </button>
            </div>
            <div>
                <button className="filter-button" onClick={() => { setFilter("All"); getTasks(); }}>
                    All
                </button>
                <button className="filter-button" onClick={() => { setFilter("Done"); getDoneNotes(); }}>
                    Done
                </button>
                <button className="filter-button" onClick={() => { setFilter("Todo"); getTodoNotes(); }}>
                    Todo
                </button>
            </div>
            <ol>
                {tasks.map((task, index) => (
                    <li key={index} className={task.isCompleted === 2 ? 'completed' : ''}>
                        {editingIndex === index ? (
                            <>
                                <input
                                    type="text"
                                    value={editingTask}
                                    onChange={handleEditInputChange}
                                />
                                <button className="save-button" onClick={saveTask}>Save</button>
                            </>
                        ) : (
                            <>
                                <input
                                    type="checkbox"
                                    checked={task.isCompleted === 2}
                                    onChange={() => updateTask(index)}
                                />
                                <span className="text">{task.text}</span>
                                <button
                                    className="delete-button"
                                    onClick={() => deleteTask(index)}
                                >
                                    🗑️
                                </button>
                                <button
                                    className="move-button"
                                    onClick={() => moveTaskUp(index)}
                                >
                                    ☝️
                                </button>
                                <button
                                    className="move-button"
                                    onClick={() => moveTaskDown(index)}
                                >
                                    👇
                                </button>
                                <button
                                    className="edit-button"
                                    onClick={() => startEditing(index)}
                                >
                                    ✏️
                                </button>
                            </>
                        )}
                    </li>
                ))}
            </ol>
            <button className="delete-all-done-button" onClick={deleteAllDoneTasks}>
                Delete all done tasks
            </button>
            <button className="delete-all-button" onClick={deleteAllTasks}>
                Delete all tasks
            </button>
        </div>
    );
}

export default ToDoList;
