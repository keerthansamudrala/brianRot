import React from 'react';
import { Search, Bell, MessageCircle, Mic, Camera } from 'lucide-react';

export default function Navbar() {
  return (
    <header className="navbar">
      {/* Search Input Container */}
      <div className="navbar-search-container">
        <Search className="search-icon" size={20} />
        <input 
          type="text" 
          placeholder="Search for brain rot stuff (skibidi, toilet, rizz, cockroaches)..." 
          className="navbar-search-bar"
        />
        <div style={{ position: 'absolute', right: '16px', top: '50%', transform: 'translateY(-50%)', display: 'flex', gap: '8px', color: '#666' }}>
          <Mic size={18} style={{ cursor: 'pointer' }} />
          <Camera size={18} style={{ cursor: 'pointer' }} />
        </div>
      </div>

      {/* Right Side Actions */}
      <div className="navbar-actions">
        <button className="icon-btn" title="Notifications">
          <Bell size={22} />
        </button>
        <button className="icon-btn" title="Chats">
          <MessageCircle size={22} />
        </button>
        
        {/* Profile Avatar with initial 'K' */}
        <div className="profile-avatar" title="Keerthan's Profile">
          K
        </div>
      </div>
    </header>
  );
}
