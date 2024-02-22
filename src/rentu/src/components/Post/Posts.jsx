import React, { useState, useEffect } from 'react';
import { dataService } from '../../modules/DataService';

const Post = () => {
    const [agencies, setAgencies] = useState([]);
    const [vehicles, setVehicles] = useState([]);
    const [selectedAgency, setSelectedAgency] = useState('');
    const [selectedVehicle, setSelectedVehicle] = useState('');
    const [description, setDescription] = useState('');
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        loadAgencies();
        loadPosts();
    }, []);

    const loadAgencies = () => {
        dataService.getAgencies()
            .then(response => {
                setAgencies(response.data);
            })
            .catch(error => {
                console.error('Error fetching agencies:', error);
            });
    };

    const loadVehiclesForAgency = (agencyId) => {
        dataService.getVehiclesByAgency(agencyId)
            .then(response => {
                setVehicles(response.data);
            })
            .catch(error => {
                console.error('Error fetching vehicles for agency:', error);
            });
    };

    const handleAgencyChange = (event) => {
        const selectedAgencyId = event.target.value;
        setSelectedAgency(selectedAgencyId);
        loadVehiclesForAgency(selectedAgencyId);
    };

    const handleVehicleChange = (event) => {
        setSelectedVehicle(event.target.value);
    };

    const handleDescriptionChange = (event) => {
        setDescription(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        const newPost = {
            agency: { id: selectedAgency },
            vehicle: { id: selectedVehicle },
            description: description
        };

        dataService.createPost(newPost)
            .then(response => {
                console.log('Post created successfully:', response.data);
                loadPosts();
            })
            .catch(error => {
                console.error('Error creating post:', error);
            });
    };

    const handleDeletePost = (postId) => {
        dataService.deletePost(postId)
            .then(response => {
                console.log(`Post with ID ${postId} deleted successfully.`);
                loadPosts();
            })
            .catch(error => {
                console.error('Error deleting post:', error);
            });
    };

    const handleUpdatePost = (postId, updatedDescription) => {
        const updatedPost = {
            description: updatedDescription
        };

        dataService.updatePost(postId, updatedPost)
            .then(response => {
                console.log(`Post with ID ${postId} updated successfully.`);
                loadPosts();
            })
            .catch(error => {
                console.error(`Error updating post with ID ${postId}:`, error);
            });
    };

    const loadPosts = () => {
        dataService.getAllPosts()
            .then(response => {
                setPosts(response.data);
            })
            .catch(error => {
                console.error('Error fetching posts:', error);
            });
    };

    return (
        <div>
            <h1>Create Post</h1>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Select Agency:</label>
                    <select value={selectedAgency} onChange={handleAgencyChange}>
                        <option value="">Select Agency</option>
                        {agencies.map(agency => (
                            <option key={agency.id} value={agency.id}>{agency.name}</option>
                        ))}
                    </select>
                </div>
                {selectedAgency && (
                    <div>
                        <label>Select Vehicle:</label>
                        <select value={selectedVehicle} onChange={handleVehicleChange}>
                            <option value="">Select Vehicle</option>
                            {vehicles.map(vehicle => (
                                <option key={vehicle.id} value={vehicle.id}>{vehicle.manufacturer} - {vehicle.model}</option>
                            ))}
                        </select>
                    </div>
                )}
                <div>
                    <label>Description:</label>
                    <textarea value={description} onChange={handleDescriptionChange}></textarea>
                </div>
                <button type="submit">Create</button>
            </form>
            <div>
                <h2>All Posts</h2>
                <ul>
                    {posts.map(post => (
                        <li key={post.id}>
                            <div>ID: {post.id}</div>
                            <div>Description: {post.description}</div>
                            <div>Agency ID: {post.agency ? post.agency.id : 'N/A'}</div>
                            <div>Agency Name: {post.agency ? post.agency.name : 'N/A'}</div>
                            <div>Vehicle ID: {post.vehicle ? post.vehicle.id : 'N/A'}</div>
                            <div>Vehicle Manufacturer: {post.vehicle ? post.vehicle.manufacturer : 'N/A'}</div>
                            <div>Vehicle Model: {post.vehicle ? post.vehicle.model : 'N/A'}</div>
                            <button onClick={() => handleDeletePost(post.id)}>Delete</button>
                        </li>
                    ))}
                </ul>
            </div>
        </div>
    );
};

export default Post;
