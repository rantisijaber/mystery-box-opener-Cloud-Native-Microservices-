import React from 'react';

interface InputFieldProps {
    label: string;
    type?: string;
    value: string;
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const InputField: React.FC<InputFieldProps> = ({ label, type = 'text', value, onChange }) => (
    <div className="input-field">
        <label className="input-label">{label}</label>
        <input
            className="input-box"
            type={type}
            value={value}
            onChange={onChange}
        />
    </div>
);

export default InputField;
