import React, { useEffect, useState } from "react";
import {
    getAllMysteryBoxes,
    getFilteredMysteryBoxes,
    buyBox,
} from "../api/mysteryBoxApi";
import { MysteryBoxCard } from "../components/MysteryBoxCard";
import type { MysteryBox } from "../types";
import { Button } from "@/components/ui/button";

export default function MysteryBoxesPage() {
    const [boxes, setBoxes] = useState<MysteryBox[]>([]);
    const [loading, setLoading] = useState(true);
    const [filter, setFilter] = useState<number | null>(null);

    const loadBoxes = async () => {
        try {
            setLoading(true);
            const data = filter
                ? await getFilteredMysteryBoxes(filter)
                : await getAllMysteryBoxes();
            setBoxes(data);
        } catch (err) {
            console.error("Error loading boxes", err);
        } finally {
            setLoading(false);
        }
    };

    const handleBuy = async (box: MysteryBox) => {
        try {
            await buyBox(box);
            alert(`You purchased ${box.name}!`);
        } catch (err) {
            alert("Failed to buy box");
        }
    };

    useEffect(() => {
        loadBoxes();
    }, [filter]);

    if (loading) return <p className="text-white">Loading...</p>;

    return (
        <div className="min-h-screen bg-gradient-to-br from-indigo-900 to-black p-6 text-white">
            <h1 className="text-2xl font-bold mb-6">Mystery Boxes</h1>

            {/* Filter controls */}
            <div className="flex gap-3 mb-6">
                <Button variant="secondary" onClick={() => setFilter(null)}>
                    All Boxes
                </Button>
                <Button variant="secondary" onClick={() => setFilter(50)}>
                    Under $50
                </Button>
                <Button variant="secondary" onClick={() => setFilter(100)}>
                    Under $100
                </Button>
            </div>

            {/* Boxes Grid */}
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
                {boxes.map((box) => (
                    <MysteryBoxCard key={box.id} box={box} onBuy={handleBuy} />
                ))}
            </div>
        </div>
    );
}
