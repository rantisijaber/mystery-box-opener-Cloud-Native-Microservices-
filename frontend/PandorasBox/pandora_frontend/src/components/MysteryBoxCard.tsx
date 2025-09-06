import React from "react";
import { Button } from "@/components/ui/button";
import type { MysteryBox } from "../types";

interface MysteryBoxCardProps {
    box: MysteryBox;
    onBuy?: (box: MysteryBox) => void;
}

export const MysteryBoxCard: React.FC<MysteryBoxCardProps> = ({ box, onBuy }) => {
    return (
        <div className="p-4 bg-white/10 rounded-xl shadow-lg text-white flex flex-col justify-between">
            <div>
                <h3 className="text-lg font-bold">{box.name}</h3>
                <p className="text-sm opacity-75">{box.description}</p>
                <p className="text-yellow-300 font-semibold mt-2">${box.price}</p>
            </div>
            {onBuy && (
                <Button className="mt-4" onClick={() => onBuy(box)}>
                    Buy Box
                </Button>
            )}
        </div>
    );
};
