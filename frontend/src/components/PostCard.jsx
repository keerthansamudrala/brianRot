import React, { useState } from 'react';

export default function PostCard({ post, onLike, imageSrc, spanClass }) {
  const [floatRoaches, setFloatRoaches] = useState([]);
  const [isActive, setIsActive] = useState(false);

  const handleLikeClick = (e) => {
    e.stopPropagation();
    
    // Call the parent state handler to increase cockroach count
    onLike(post.post_id);
    
    // Trigger pop-up/like animations
    setIsActive(true);
    setTimeout(() => setIsActive(false), 200);

    // Create a new floating cockroach
    const rect = e.currentTarget.getBoundingClientRect();
    const parentRect = e.currentTarget.parentElement.getBoundingClientRect();
    
    // Calculate click coordinates relative to the card container
    const x = e.clientX - parentRect.left;
    const y = e.clientY - parentRect.top;
    
    const id = Date.now() + Math.random();
    const newRoach = {
      id,
      x,
      y,
      rot: Math.random() * 360,
      rotEnd: Math.random() * 360 + (Math.random() > 0.5 ? 90 : -90)
    };

    setFloatRoaches((prev) => [...prev, newRoach]);

    // Clean up after animation finished (1000ms in CSS)
    setTimeout(() => {
      setFloatRoaches((prev) => prev.filter((r) => r.id !== id));
    }, 1000);
  };

  return (
    <article className={`post-card ${spanClass || 'span-medium'}`}>
      {/* Post Image */}
      <div className="post-card-image-wrapper">
        <img 
          src={imageSrc || `https://picsum.photos/id/${(post.post_id * 20) % 100}/300/400`} 
          alt={post.post_title} 
          className="post-card-image"
          loading="lazy"
        />
      </div>

      {/* Post Content */}
      <div className="post-card-content">
        <h3 className="post-card-title">{post.post_title}</h3>
        
        <div className="post-card-footer">
          {/* Roach Liked Display */}
          <div className={`post-card-roaches ${isActive ? 'active' : ''}`}>
            <span>🪳</span>
            <span>{post.post_cockroaches}</span>
          </div>

          {/* Interact Button */}
          <button 
            className="btn-roach-like" 
            onClick={handleLikeClick} 
            title="Spend a cockroach on this post!"
          >
            <span>+🪳</span>
          </button>
        </div>
      </div>

      {/* Render Floating Cockroaches */}
      {floatRoaches.map((roach) => (
        <span
          key={roach.id}
          className="floating-roach"
          style={{
            left: `${roach.x}px`,
            top: `${roach.y}px`,
            '--rot': `${roach.rot}deg`,
            '--rot-end': `${roach.rotEnd}deg`
          }}
        >
          🪳
        </span>
      ))}
    </article>
  );
}
