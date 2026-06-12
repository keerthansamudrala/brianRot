import React from 'react';
import { NavLink } from 'react-router-dom';
import { Home, Grid, Bell, MessageSquare, Plus, Settings } from 'lucide-react';

export default function Sidebar() {
  return (
    <aside className="sidebar">
      {/* Custom Cockroach / Brain Rot SVG Logo */}
      <div className="sidebar-logo" title="brainRot Home">
        <svg viewBox="0 0 100 100" width="36" height="36" fill="currentColor">
          {/* Stylized cockroach shape combined with brain curves */}
          <path d="M50,20 C42,20 35,27 35,35 C35,38 36,40 37,42 C30,47 25,55 25,65 C25,75 35,80 50,80 C65,80 75,75 75,65 C75,55 70,47 63,42 C64,40 65,38 65,35 C65,27 58,20 50,20 Z" fill="#F4CE14" />
          {/* Eyes */}
          <circle cx="43" cy="32" r="3" fill="#121212" />
          <circle cx="57" cy="32" r="3" fill="#121212" />
          {/* Antennae */}
          <path d="M45,22 Q30,10 20,15" stroke="#F4CE14" strokeWidth="2" fill="none" strokeLinecap="round" />
          <path d="M55,22 Q70,10 80,15" stroke="#F4CE14" strokeWidth="2" fill="none" strokeLinecap="round" />
          {/* Legs */}
          <path d="M30,50 Q15,48 10,52" stroke="#F4CE14" strokeWidth="2" fill="none" strokeLinecap="round" />
          <path d="M30,60 Q12,62 8,68" stroke="#F4CE14" strokeWidth="2" fill="none" strokeLinecap="round" />
          <path d="M30,70 Q15,76 12,84" stroke="#F4CE14" strokeWidth="2" fill="none" strokeLinecap="round" />
          
          <path d="M70,50 Q85,48 90,52" stroke="#F4CE14" strokeWidth="2" fill="none" strokeLinecap="round" />
          <path d="M70,60 Q88,62 92,68" stroke="#F4CE14" strokeWidth="2" fill="none" strokeLinecap="round" />
          <path d="M70,70 Q85,76 88,84" stroke="#F4CE14" strokeWidth="2" fill="none" strokeLinecap="round" />
          {/* Brain patterns on back */}
          <path d="M46,45 Q50,42 54,45 M42,55 Q50,50 58,55 M44,65 Q50,62 56,65" stroke="#3C1610" strokeWidth="2" fill="none" strokeLinecap="round" />
        </svg>
      </div>

      <nav className="sidebar-menu">
        <NavLink 
          to="/" 
          className={({ isActive }) => `sidebar-item ${isActive ? 'active' : ''}`}
          title="Home"
        >
          <Home size={24} />
        </NavLink>
        
        <NavLink 
          to="/posts" 
          className={({ isActive }) => `sidebar-item ${isActive ? 'active' : ''}`}
          title="Browse Posts"
        >
          <Grid size={24} />
        </NavLink>

        <div className="sidebar-item" title="Create Post">
          <Plus size={24} />
        </div>

        <div className="sidebar-item" title="Notifications">
          <Bell size={24} />
        </div>

        <div className="sidebar-item" title="Messages">
          <MessageSquare size={24} />
        </div>
      </nav>

      <div className="sidebar-footer" title="Settings">
        <Settings size={24} />
      </div>
    </aside>
  );
}
