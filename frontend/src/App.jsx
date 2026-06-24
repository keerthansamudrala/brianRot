import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Sidebar from './components/Sidebar';
import Navbar from './components/Navbar';
import CockroachCanvas from './components/CockroachCanvas';
import Home from './pages/Home';
import Posts from './pages/Posts';
import './App.css';

function App() {
  return (
    <Router>
      <div className="app-container">
        {/* Animated Background Canvas */}
        <CockroachCanvas />

        {/* Navigation Elements */}
        <Sidebar />
        <Navbar />

        {/* Main Content Area */}
        <main className="main-content">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/posts" element={<Posts />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
