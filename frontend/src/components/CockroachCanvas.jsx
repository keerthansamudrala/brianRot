import React, { useEffect, useRef } from 'react';

export default function CockroachCanvas() {
  const canvasRef = useRef(null);

  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    const ctx = canvas.getContext('2d');
    let animationFrameId;
    let width = (canvas.width = window.innerWidth);
    let height = (canvas.height = window.innerHeight);

    // Track mouse position
    const mouse = { x: -1000, y: -1000, active: false };

    const handleMouseMove = (e) => {
      mouse.x = e.clientX;
      mouse.y = e.clientY;
      mouse.active = true;
    };

    const handleMouseLeave = () => {
      mouse.active = false;
      mouse.x = -1000;
      mouse.y = -1000;
    };

    const handleResize = () => {
      width = canvas.width = window.innerWidth;
      height = canvas.height = window.innerHeight;
    };

    window.addEventListener('mousemove', handleMouseMove);
    window.addEventListener('mouseleave', handleMouseLeave);
    window.addEventListener('resize', handleResize);

    // Cockroach Class
    class Cockroach {
      constructor() {
        this.reset(true);
      }

      reset(init = false) {
        // Start randomly on screen, or just off-screen if resetting during run
        if (init) {
          this.x = Math.random() * width;
          this.y = Math.random() * height;
        } else {
          // Spawn near an edge
          const edge = Math.floor(Math.random() * 4);
          if (edge === 0) { // Top
            this.x = Math.random() * width;
            this.y = -20;
          } else if (edge === 1) { // Right
            this.x = width + 20;
            this.y = Math.random() * height;
          } else if (edge === 2) { // Bottom
            this.x = Math.random() * width;
            this.y = height + 20;
          } else { // Left
            this.x = -20;
            this.y = Math.random() * height;
          }
        }

        this.setNewTarget();
        this.speed = 0.5 + Math.random() * 0.8;
        this.angle = 0;
        this.scale = 0.6 + Math.random() * 0.4;
        this.legWiggle = Math.random() * 100;
        this.state = 'crawling'; // 'crawling', 'idle', 'scared'
        this.stateTimer = 100 + Math.random() * 200;
      }

      setNewTarget() {
        this.targetX = Math.random() * width;
        this.targetY = Math.random() * height;
      }

      update() {
        this.legWiggle += this.state === 'scared' ? 0.4 : 0.12;
        this.stateTimer--;

        // Check distance to mouse
        const dxMouse = this.x - mouse.x;
        const dyMouse = this.y - mouse.y;
        const distMouse = Math.sqrt(dxMouse * dxMouse + dyMouse * dyMouse);

        if (mouse.active && distMouse < 120) {
          // Scared state
          this.state = 'scared';
          this.stateTimer = 80;
          // Run away from mouse
          const angleToMouse = Math.atan2(dyMouse, dxMouse);
          this.targetX = this.x + Math.cos(angleToMouse) * 200;
          this.targetY = this.y + Math.sin(angleToMouse) * 200;
        } else if (this.stateTimer <= 0) {
          // Transition state
          if (this.state === 'crawling') {
            this.state = 'idle';
            this.stateTimer = 50 + Math.random() * 100;
          } else {
            this.state = 'crawling';
            this.stateTimer = 150 + Math.random() * 200;
            this.setNewTarget();
          }
        }

        if (this.state === 'crawling' || this.state === 'scared') {
          // Move towards target
          const dx = this.targetX - this.x;
          const dy = this.targetY - this.y;
          const dist = Math.sqrt(dx * dx + dy * dy);

          if (dist > 5) {
            const currentSpeed = this.state === 'scared' ? this.speed * 4.5 : this.speed;
            
            // Adjust angle smoothly towards target
            const targetAngle = Math.atan2(dy, dx) + Math.PI / 2;
            let angleDiff = targetAngle - this.angle;
            
            // Normalize angle diff to -PI to PI
            while (angleDiff < -Math.PI) angleDiff += Math.PI * 2;
            while (angleDiff > Math.PI) angleDiff -= Math.PI * 2;
            
            this.angle += angleDiff * 0.1; // Smooth turning
            
            // Move forward (based on cockroach's facing direction)
            this.x += Math.sin(this.angle) * currentSpeed;
            this.y -= Math.cos(this.angle) * currentSpeed;
          } else {
            if (this.state === 'scared') {
              this.state = 'crawling';
              this.stateTimer = 100;
            }
            this.setNewTarget();
          }
        } else {
          // Idle - just slight antenna movement
          this.legWiggle += 0.02;
        }

        // Keep bounds or reset if way offscreen
        const buffer = 50;
        if (this.x < -buffer || this.x > width + buffer || this.y < -buffer || this.y > height + buffer) {
          this.reset(false);
        }
      }

      draw(c) {
        c.save();
        c.translate(this.x, this.y);
        c.rotate(this.angle);
        c.scale(this.scale, this.scale);

        // Cockroach Colors
        const bodyColor = '#3C1610';
        const wingColor = '#52231A';
        const legColor = '#240C08';

        // 1. Antennae
        c.strokeStyle = legColor;
        c.lineWidth = 1;
        c.beginPath();
        // Left antenna
        c.moveTo(-4, -15);
        c.quadraticCurveTo(-12, -28 - (Math.sin(this.legWiggle * 0.3) * 4), -16, -38);
        // Right antenna
        c.moveTo(4, -15);
        c.quadraticCurveTo(12, -28 + (Math.sin(this.legWiggle * 0.3) * 4), 16, -38);
        c.stroke();

        // 2. Legs (6 legs, wiggle animation when moving)
        c.strokeStyle = legColor;
        c.lineWidth = 1.8;
        const isMoving = this.state !== 'idle';
        const wiggle = isMoving ? Math.sin(this.legWiggle) * 6 : Math.sin(this.legWiggle * 0.2) * 1.5;

        // Front legs
        c.beginPath();
        c.moveTo(-5, -5);
        c.lineTo(-16 - wiggle, -9);
        c.moveTo(5, -5);
        c.lineTo(16 - wiggle, -9);
        
        // Middle legs
        c.moveTo(-6, 2);
        c.lineTo(-18 + wiggle, 2);
        c.moveTo(6, 2);
        c.lineTo(18 + wiggle, 2);

        // Back legs
        c.moveTo(-5, 9);
        c.lineTo(-20 - wiggle, 17);
        c.moveTo(5, 9);
        c.lineTo(20 - wiggle, 17);
        c.stroke();

        // 3. Body Oval
        c.fillStyle = bodyColor;
        c.beginPath();
        c.ellipse(0, 0, 7, 15, 0, 0, Math.PI * 2);
        c.fill();

        // 4. Head
        c.fillStyle = '#1A0704';
        c.beginPath();
        c.ellipse(0, -13, 4.5, 3.5, 0, 0, Math.PI * 2);
        c.fill();

        // 5. Wings (overlapping on top)
        c.fillStyle = wingColor;
        c.beginPath();
        c.ellipse(-1.8, 1, 4.5, 13, 0.04, 0, Math.PI * 2);
        c.ellipse(1.8, 1, 4.5, 13, -0.04, 0, Math.PI * 2);
        c.fill();

        c.restore();
      }
    }

    // Initialize cockroach army
    const cockroachCount = 35;
    const cockroaches = Array.from({ length: cockroachCount }, () => new Cockroach());

    // Loop
    const tick = () => {
      ctx.clearRect(0, 0, width, height);

      // We don't draw the background yellow here because the CSS body does it.
      // This leaves the canvas transparent for overlay.
      cockroaches.forEach((roach) => {
        roach.update();
        roach.draw(ctx);
      });

      animationFrameId = requestAnimationFrame(tick);
    };

    tick();

    // Clean up
    return () => {
      window.removeEventListener('mousemove', handleMouseMove);
      window.removeEventListener('mouseleave', handleMouseLeave);
      window.removeEventListener('resize', handleResize);
      cancelAnimationFrame(animationFrameId);
    };
  }, []);

  return (
    <canvas
      ref={canvasRef}
      style={{
        position: 'fixed',
        top: 0,
        left: 0,
        width: '100vw',
        height: '100vh',
        zIndex: 1,
        pointerEvents: 'none',
      }}
    />
  );
}
