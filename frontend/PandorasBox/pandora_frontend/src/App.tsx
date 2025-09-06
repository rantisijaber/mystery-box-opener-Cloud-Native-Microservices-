import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import LoginPage from './pages/LoginPage.tsx';
import SignupPage from './pages/SignupPage.tsx';
import './App.css'
import './auth.css'
import MysteryBoxesPage from "./pages/MysteryBoxPage.tsx";
import BadgesPage from "./pages/BadgesPage.tsx";
const App: React.FC = () => {
    return (
        <Router>
            <Routes>
                <Route path="/login" element={<LoginPage />} />
                <Route path="/signup" element={<SignupPage />} />
                <Route path="*" element={<Navigate to="/login" />} />
                <Route path="/badges" element={<BadgesPage />} />
                <Route path="/boxes" element={<MysteryBoxesPage />} />

            </Routes>
        </Router>
    );
};

export default App;
