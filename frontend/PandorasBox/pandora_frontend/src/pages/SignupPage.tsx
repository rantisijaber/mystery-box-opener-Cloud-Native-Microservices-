
import React, { useState } from 'react';
import AuthForm from '../components/AuthForm';
import {Link} from "react-router-dom";

const SignupPage: React.FC = () => {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleSignup = async () => {
        console.log('Signing up with:', email, password);
        // TODO: AWS Cognito signup logic here
    };

    return (
        <div className="auth-container">
            <h1>Pandora's Box</h1>
            <AuthForm
                mode="signup"
                email={email}
                password={password}
                onEmailChange={(e) => setEmail(e.target.value)}
                onPasswordChange={(e) => setPassword(e.target.value)}
                onSubmit={handleSignup}
            />
            <p className="text-link">
                Already have an account? <Link to="/">Log in here</Link>
            </p>
        </div>
    );
};

export default SignupPage;

