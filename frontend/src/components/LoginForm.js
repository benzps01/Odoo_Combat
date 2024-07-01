import React, { useState } from 'react';
import axios from 'axios';

const LoginForm = () => {
  const initialState = {
    userName: '',
    password: '',
  };

  const [formData, setFormData] = useState(initialState);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // const form = new FormData();
    // form.append('userName', formData.uname);
    // form.append('password', formData.password);

    try {
      const response = await axios.post(
        'http://localhost:8080/login',
        formData,
        {
          headers: {
            'Content-Type': 'application/json',
          },
        }
      );
      console.log(response.data);
    } catch (error) {
      console.error('Something went wrong: ', error);
    }
    setFormData(initialState);
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <div>
          <label>UserName: </label>
          <input
            type='text'
            name='userName'
            value={formData.uname}
            onChange={handleChange}
          />
        </div>
        <div>
          <label>Password: </label>
          <input
            type='password'
            name='password'
            value={formData.password}
            onChange={handleChange}
          />
        </div>
        <button type='submit'>Submit</button>
      </form>
    </div>
  );
};

export default LoginForm;
