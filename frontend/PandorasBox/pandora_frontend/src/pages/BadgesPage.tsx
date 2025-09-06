import React, { useEffect, useState } from "react";
import { getMyBadges, sellBadge } from "../api/badgeApi";
import { BadgeList } from "../components/BadgeList";
import type { UserBadge } from "../types";

export default function BadgesPage() {
    const [myBadges, setMyBadges] = useState<UserBadge[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState<string | null>(null);

    const loadBadges = async () => {
        try {
            setLoading(true);
            const data = await getMyBadges();
            setMyBadges(data);
        } catch (err) {
            setError("Failed to load badges");
        } finally {
            setLoading(false);
        }
    };

    const handleSell = async (id: string) => {
        try {
            await sellBadge(id);
            setMyBadges((prev) => prev.filter((b) => b.id !== id));
        } catch (err) {
            alert("Failed to sell badge");
        }
    };

    useEffect(() => {
        loadBadges();
    }, []);

    if (loading) return <p className="text-white">Loading...</p>;
    if (error) return <p className="text-red-400">{error}</p>;

    return (
        <div className="min-h-screen bg-gradient-to-br from-gray-900 to-black p-6 text-white">
            <h1 className="text-2xl font-bold mb-6">My Badges</h1>
            <BadgeList badges={myBadges} onSell={handleSell} />
        </div>
    );
}
