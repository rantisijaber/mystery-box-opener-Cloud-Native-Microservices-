import type { Badge, UserBadge } from "../types";

const API_URL = import.meta.env.VITE_API_URL; // e.g., https://gateway.yourdomain.com/api

function authHeader() {
    const token = localStorage.getItem("accessToken");
    return token ? { Authorization: `Bearer ${token}` } : {};
}

export async function getAllBadges(): Promise<Badge[]> {
    const res = await fetch(`${API_URL}/badge/all-badges`, {
        headers: { "Content-Type": "application/json", ...authHeader() },
    });
    if (!res.ok) throw new Error("Failed to fetch all badges");
    return res.json();
}

export async function getMyBadges(): Promise<UserBadge[]> {
    const res = await fetch(`${API_URL}/badge/my-badges`, {
        headers: { "Content-Type": "application/json", ...authHeader() },
    });
    if (!res.ok) throw new Error("Failed to fetch my badges");
    return res.json();
}

export async function sellBadge(myBadgeID: string): Promise<void> {
    const res = await fetch(`${API_URL}/badge/sell-badge?myBadgeID=${myBadgeID}`, {
        method: "DELETE",
        headers: { ...authHeader() },
    });
    if (!res.ok) throw new Error("Failed to sell badge");
}
