import React, { useState } from 'react';
import AuthForm from '../components/AuthForm';
import { Link } from 'react-router-dom';

const LoginPage: React.FC = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async () => {
        console.log('Logging in with:', email, password);
        // TODO: AWS Cognito login logic here
    };

    return (
        <div className="auth-container">
            <h1>Pandora's Box</h1>
            <AuthForm
                mode="login"
                email={email}
                password={password}
                onEmailChange={(e) => setEmail(e.target.value)}
                onPasswordChange={(e) => setPassword(e.target.value)}
                onSubmit={handleLogin}
            />
            <p className="text-link">
                Don't have an account? <Link to="/signup">Sign up here</Link>
            </p>
        </div>
    );
};

export default LoginPage;

