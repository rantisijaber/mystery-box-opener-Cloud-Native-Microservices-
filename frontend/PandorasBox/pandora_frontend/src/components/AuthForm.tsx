import React from 'react';
import InputField from './InputField';

interface AuthFormProps {
    mode: 'login' | 'signup';
    email: string;
    password: string;
    onEmailChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    onPasswordChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    onSubmit: () => void;
}

const AuthForm: React.FC<AuthFormProps> = ({
                                               mode,
                                               email,
                                               password,
                                               onEmailChange,
                                               onPasswordChange,
                                               onSubmit,
                                           }) => {
    return (
        <form
            onSubmit={(e) => {
                e.preventDefault();
                onSubmit();
            }}
            className="auth-form"
        >
            <h2 className="form-title">{mode === 'login' ? 'Login' : 'Sign Up'}</h2>
            <InputField label="Email" type="email" value={email} onChange={onEmailChange} />
            <InputField label="Password" type="password" value={password} onChange={onPasswordChange} />
            <button type="submit" className="submit-button">
                {mode === 'login' ? 'Log In' : 'Sign Up'}
            </button>
        </form>
    );
};

export default AuthForm;
