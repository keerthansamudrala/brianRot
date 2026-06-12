import React, { useState, useEffect } from 'react';
import PostCard from '../components/PostCard';
import heroImg from '../assets/hero.png';
import villainImg from '../assets/villain.png';

export default function Posts() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [usingFallback, setUsingFallback] = useState(false);

  // Fallback mock posts as requested and with additional visual items for Pinterest layout
  const mockPosts = [
    {
      post_id: 1,
      post_title: "birth of the hero",
      post_cockroaches: 100,
      image: heroImg,
      span: "span-long"
    },
    {
      post_id: 2,
      post_title: "birth of the villian",
      post_cockroaches: 209,
      image: villainImg,
      span: "span-medium"
    },
    {
      post_id: 3,
      post_title: "average brain rot enjoyer",
      post_cockroaches: 147,
      image: "https://images.unsplash.com/photo-1531746020798-e6953c6e8e04?w=500&auto=format&fit=crop&q=60",
      span: "span-short"
    },
    {
      post_id: 4,
      post_title: "sigma grindset cockroach simulator",
      post_cockroaches: 420,
      image: "https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe?w=500&auto=format&fit=crop&q=60",
      span: "span-long"
    },
    {
      post_id: 5,
      post_title: "buying things with cockroaches",
      post_cockroaches: 88,
      image: "https://images.unsplash.com/photo-1550745165-9bc0b252726f?w=500&auto=format&fit=crop&q=60",
      span: "span-medium"
    },
    {
      post_id: 6,
      post_title: "me and the boys rotting",
      post_cockroaches: 1003,
      image: "https://images.unsplash.com/photo-1568602471122-7832951cc4c5?w=500&auto=format&fit=crop&q=60",
      span: "span-short"
    }
  ];

  useEffect(() => {
    fetchPosts();
  }, []);

  const fetchPosts = async () => {
    setLoading(true);
    setError(null);
    try {
      // Fetching from localhost:8080/posts
      const response = await fetch('http://localhost:8080/posts');
      if (!response.ok) {
        throw new Error(`Server error: ${response.statusText}`);
      }
      const data = await response.json();
      
      // Map API data and add layout/image properties
      const mappedData = data.map((post) => {
        // Map images to the hero and villain respectively
        let image = null;
        if (post.post_title.toLowerCase().includes('hero')) {
          image = heroImg;
        } else if (post.post_title.toLowerCase().includes('villian') || post.post_title.toLowerCase().includes('villain')) {
          image = villainImg;
        } else {
          image = `https://picsum.photos/id/${(post.post_id * 23) % 100}/300/400`;
        }

        // Pinterest layout row spans
        const spans = ["span-short", "span-medium", "span-long"];
        const span = spans[post.post_id % 3];

        return {
          ...post,
          image,
          span
        };
      });

      setPosts(mappedData);
      setUsingFallback(false);
    } catch (err) {
      console.warn("Backend API not reachable. Falling back to mock data.", err);
      setPosts(mockPosts);
      setUsingFallback(true);
    } finally {
      setLoading(false);
    }
  };

  const handleLike = async (postId) => {
    // Optimistic local update
    setPosts((prevPosts) =>
      prevPosts.map((post) =>
        post.post_id === postId
          ? { ...post, post_cockroaches: post.post_cockroaches + 1 }
          : post
      )
    );

    if (!usingFallback) {
      try {
        // Send a POST request to update cockroach count (likes) on the backend
        await fetch(`http://localhost:8080/posts/like?id=${postId}`, {
          method: 'POST'
        });
      } catch (err) {
        console.error("Failed to send like update to backend:", err);
      }
    }
  };

  if (loading) {
    return (
      <div className="loader-container">
        <div className="spinner">🪳</div>
        <p className="posts-subtitle">Loading cockroach feed...</p>
      </div>
    );
  }

  return (
    <div className="posts-page">
      <div className="posts-page-header">
        <h2 className="posts-title">Cockroach Feed 🪳</h2>
        <p className="posts-subtitle">
          {usingFallback 
            ? "Offline Mode - Showing Cockroach Mock Data" 
            : "Live Connection - Active Backend"}
        </p>
      </div>

      <div className="posts-grid">
        {posts.map((post) => (
          <PostCard
            key={post.post_id}
            post={post}
            onLike={handleLike}
            imageSrc={post.image}
            spanClass={post.span}
          />
        ))}
      </div>
    </div>
  );
}
