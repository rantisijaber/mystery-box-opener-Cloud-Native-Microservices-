import type { MysteryBox } from "../types";

const API_URL = import.meta.env.VITE_API_URL;

function authHeader() {
    const token = localStorage.getItem("accessToken");
    return token ? { Authorization: `Bearer ${token}` } : {};
}

export async function getAllMysteryBoxes(): Promise<MysteryBox[]> {
    const res = await fetch(`${API_URL}/mystery-box/all-boxes`, {
        headers: { "Content-Type": "application/json", ...authHeader() },
    });
    if (!res.ok) throw new Error("Failed to fetch mystery boxes");
    return res.json();
}

export async function getFilteredMysteryBoxes(maxPrice: number): Promise<MysteryBox[]> {
    const res = await fetch(`${API_URL}/mystery-box/filtered-boxes?maxPrice=${maxPrice}`, {
        headers: { "Content-Type": "application/json", ...authHeader() },
    });
    if (!res.ok) throw new Error("Failed to fetch filtered boxes");
    return res.json();
}

export async function buyBox(box: MysteryBox): Promise<void> {
    const res = await fetch(`${API_URL}/mystery-box/buy-box`, {
        method: "POST",
        headers: { "Content-Type": "application/json", ...authHeader() },
        body: JSON.stringify(box),
    });
    if (!res.ok) throw new Error("Failed to buy box");
}
