import React from "react";
import { Button } from "@/components/ui/button";
import type { UserBadge } from "../types";

interface BadgeListProps {
    badges: UserBadge[];
    onSell?: (id: string) => void;
}

export const BadgeList: React.FC<BadgeListProps> = ({ badges, onSell }) => {
    return (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            {badges.map((userBadge) => (
                <div
                    key={userBadge.id}
                    className="p-4 rounded-xl bg-white/10 text-white shadow-lg flex flex-col justify-between"
                >
                    <div>
                        <h3 className="text-lg font-bold">{userBadge.badge.name}</h3>
                        <p className="text-sm opacity-75">{userBadge.badge.description}</p>
                        <span className="text-xs uppercase text-yellow-300">
              {userBadge.badge.rarity}
            </span>
                    </div>
                    {onSell && (
                        <Button
                            className="mt-3"
                            variant="destructive"
                            onClick={() => onSell(userBadge.id)}
                        >
                            Sell
                        </Button>
                    )}
                </div>
            ))}
        </div>
    );
};
