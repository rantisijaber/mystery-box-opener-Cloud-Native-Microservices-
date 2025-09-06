export interface Badge {
    id: string;
    name: string;
    description: string;
    rarity: string;
}

export interface UserBadge {
    id: string;          // unique badge ID owned by user
    badge: Badge;        // reference to badge
    acquiredAt: string;  // timestamp
}

export interface MysteryBox {
    id: string;
    name: string;
    description: string;
    price: number;
    rarity: string; // if you have rarity tiers
}
