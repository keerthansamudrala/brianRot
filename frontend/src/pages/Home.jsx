import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { ArrowRight } from 'lucide-react';

export default function Home() {
  const navigate = useNavigate();
  const [displayText, setDisplayText] = useState('');
  const [isDeleting, setIsDeleting] = useState(false);
  const fullText = 'ready to rot your brain';
  const typingSpeed = 120;
  const deletingSpeed = 70;
  const pauseDuration = 2000;

  useEffect(() => {
    let timer;
    
    if (!isDeleting && displayText !== fullText) {
      // Typing phase
      timer = setTimeout(() => {
        setDisplayText(fullText.substring(0, displayText.length + 1));
      }, typingSpeed);
    } else if (isDeleting && displayText !== '') {
      // Deleting phase
      timer = setTimeout(() => {
        setDisplayText(fullText.substring(0, displayText.length - 1));
      }, deletingSpeed);
    } else if (displayText === fullText) {
      // Pause at full text
      timer = setTimeout(() => {
        setIsDeleting(true);
      }, pauseDuration);
    } else if (displayText === '') {
      // Pause at empty text before typing again
      timer = setTimeout(() => {
        setIsDeleting(false);
      }, 500);
    }

    return () => clearTimeout(timer);
  }, [displayText, isDeleting]);

  return (
    <div className="home-page">
      <div className="hero-card">
        <div>
          <h1 className="home-title">welcome cockraoches</h1>
          <p className="home-subtitle">
            you got <span className="highlight-count">100</span> cockroaches to spent
          </p>
        </div>

        {/* Typewriter Animation */}
        <div className="typewriter-container">
          <span className="typewriter-text">{displayText}</span>
        </div>

        {/* Navigation Button */}
        <button className="btn-primary" onClick={() => navigate('/posts')}>
          View Posts <ArrowRight size={20} style={{ marginLeft: '8px', verticalAlign: 'middle', display: 'inline' }} />
        </button>
      </div>
    </div>
  );
}
