-- incrementInvest.lua
local currentAmount = tonumber(redis.call('GET',KEYS[1]))
if currentAmount == nil then currentAmount = 0 end

local maxAmount = tonumber(ARGV[1])
local toAmount = tostring(ARGV[2])
local sumAmount = currentAmount + toAmount;
if maxAmount >= sumAmount
then
    redis.call('SET',KEYS[1],currentAmount+toAmount)
    return true;
end
return false

